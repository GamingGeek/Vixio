package me.iblitzkriegi.vixio.registration;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Converter;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.registrations.Converters;
import net.dv8tion.jda.entities.*;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

/**
 * Created by Blitz on 10/30/2016.
 */
public class TypesAndConverter {
    public static void setupTypes(){
        Converters.registerConverter(User.class, String.class, (Converter<User, String>) u -> u.getId());
        Converters.registerConverter(Guild.class, String.class, (Converter<Guild, String>) u -> u.getId());
        Converters.registerConverter(Channel.class, String.class, (Converter<Channel, String>) u -> u.getId());
        Converters.registerConverter(PrivateChannel.class, String.class, (Converter<PrivateChannel, String>) u -> u.getId());
        Converters.registerConverter(Message.class, String.class, (Converter<Message, String>) u -> u.getId());
        Classes.registerClass(new ClassInfo<>(Message.class, "message")
                .name("message").parser(new Parser<Message>() {
                    @Override
                    @Nullable
                    public Message parse(String s, ParseContext context) {
                        return null;
                    }

                    @Override
                    public String toString(Message msg, int flags) {
                        return msg.getStrippedContent();
                    }

                    @Override
                    public String toVariableNameString(Message msg) {
                        return msg.getStrippedContent();
                    }

                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }

                }));
        Classes.registerClass(new ClassInfo<>(User.class, "user")
                .name("user").parser(new Parser<User>() {
                    @Override
                    @Nullable
                    public User parse(String s, ParseContext context) {
                        return null;
                    }

                    @Override
                    public String toString(User usr, int flags) {
                        return usr.getId();
                    }

                    @Override
                    public String toVariableNameString(User usr) {
                        return usr.getId();
                    }

                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }

                }));
    }
}

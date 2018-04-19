package me.iblitzkriegi.vixio.effects.message;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.iblitzkriegi.vixio.Vixio;
import me.iblitzkriegi.vixio.util.Util;
import me.iblitzkriegi.vixio.util.skript.AsyncEffect;
import me.iblitzkriegi.vixio.util.wrapper.Bot;
import me.iblitzkriegi.vixio.util.wrapper.Emote;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageReaction;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.exceptions.PermissionException;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import org.bukkit.event.Event;

public class EffRemoveReaction extends AsyncEffect {
    static {
        Vixio.getInstance().registerEffect(EffRemoveReaction.class, "remove %emote% added by %user% from %message% [with %bot/string%]")
                .setName("Remove Emote by User")
                .setDesc("Remove a specific users emote from a message, this is for removing a users reacted emote in the reaction add event mostly.")
                .setExample(
                        "on reaction added:",
                        "\tremove event-emote added by event-user from event-message"
                );
    }

    private Expression<Emote> emote;
    private Expression<User> user;
    private Expression<Message> message;
    private Expression<Object> bot;

    @Override
    protected void execute(Event e) {
        Emote emote = this.emote.getSingle(e);
        User user = this.user.getSingle(e);
        Message message = this.message.getSingle(e);
        Bot bot = Util.botFrom(this.bot.getSingle(e));
        if (emote == null || user == null || message == null || bot == null) {
            return;
        }
        Util.bindMessage(bot, message).queue(bindedMessage -> {
            if (bindedMessage == null) {
                return;
            }
            for (MessageReaction messageReaction : bindedMessage.getReactions()) {
                if (messageReaction.getReactionEmote().getName().equalsIgnoreCase(emote.getName())) {
                    try {
                        messageReaction.removeReaction(user).queue();
                    } catch (PermissionException x) {
                        Vixio.getErrorHandler().needsPerm(bot, "remove reaction from user", x.getPermission().getName());
                    }
                }
            }
        });
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "remove " + emote.toString(e, debug) + " added by " + user.toString(e, debug) + " from " + message.toString(e, debug) + " with " + bot.toString(e, debug);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        emote = (Expression<Emote>) exprs[0];
        user = (Expression<User>) exprs[1];
        message = (Expression<Message>) exprs[2];
        bot = (Expression<Object>) exprs[3];
        return true;
    }
}

package me.iblitzkriegi.vixio.expressions.botInfo;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.iblitzkriegi.vixio.effects.EffLogin;
import me.iblitzkriegi.vixio.registration.ExprAnnotation;
import net.dv8tion.jda.JDA;
import org.bukkit.event.Event;

import java.util.Date;

import static me.iblitzkriegi.vixio.effects.EffLogin.bots;

/**
 * Created by Blitz on 11/2/2016.
 */
@ExprAnnotation.Expression(returntype = String.class, type = ExpressionType.SIMPLE, syntax = "(amount|size) of %string%['s] guilds")
public class ExprGuildSize extends SimpleExpression<String> {
    private Expression<String> vBotName;
    @Override
    protected String[] get(Event e) {
        return new String[]{getSize(e)};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return getClass().getName();
    }

    @Override
    public boolean init(Expression<?>[] expr, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        vBotName = (Expression<String>) expr[0];
        return true;
    }
    private String getSize(Event e){
        JDA jda = bots.get(vBotName.getSingle(e));
        return String.valueOf(jda.getGuilds().size());
    }
}

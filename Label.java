import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;
/**
 * The Label class is used for text centering.
 *
 * @author Zamoht (Credit to danpost)
 * @version 1.0
 *
 */
public class Label extends Actor
{
    /**
     * Constructs a new Label object from a string, a font and a color.
     */
    public Label(String text, Font font, Color textColor)
    {
        getImage().setFont(font);
        FontRenderContext frc = getImage().getAwtImage().createGraphics().getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(text, frc);
        GreenfootImage label = new GreenfootImage((int)bounds.getWidth() + 2, (int)bounds.getHeight() + 2);
        label.setColor(textColor);
        label.setFont(font);
        LineMetrics lm = font.getLineMetrics(text, frc);
        label.drawString(text, 1, Math.round(label.getHeight() + lm.getStrikethroughOffset() + lm.getStrikethroughThickness() - 1));
        setImage(label);
    }

    public void setText(String text)
    {
        Font font = getImage().getFont();
        Color textColor = getImage().getColor();
        FontRenderContext frc = getImage().getAwtImage().createGraphics().getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(text, frc);
        GreenfootImage label = new GreenfootImage((int)bounds.getWidth() + 2, (int)bounds.getHeight() + 2);
        label.setColor(textColor);
        label.setFont(font);
        LineMetrics lm = font.getLineMetrics(text, frc);
        label.drawString(text, 1, Math.round(label.getHeight() + lm.getStrikethroughOffset() + lm.getStrikethroughThickness() - 1));
        setImage(label);
    }
}

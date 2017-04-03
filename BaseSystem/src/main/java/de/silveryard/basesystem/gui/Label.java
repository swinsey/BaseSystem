package de.silveryard.basesystem.gui;

import de.silveryard.basesystem.util.IDisposable;

import java.util.ArrayList;
import java.util.List;

import static de.silveryard.basesystem.gui.SDLLabel.*;
import static de.silveryard.basesystem.gui.SDLWindow.windowDrawLabel;

/**
 * Created by beppo on 04/02/17.
 */
public class Label extends RenderObject implements IDisposable, IMoveable, ISizeable, IFadeable {
    public static Label create(Font font, String text,
                        HorizontalAlignment horizontalAlignment,
                        VerticalAlignment verticalAlignment,
                        int x, int y, int width, int height,
                        byte r, byte g, byte b, byte a){
        int result = labelCreate(font.getId(), text, r, g, b, a, width);
        if(result < 0){
            //TODO do error reporting
            return null;
        }

        return new Label(result, font, text,
                horizontalAlignment, verticalAlignment,
                x, y, width, height,
                r, g, b, a);
    }

    public static void fitLabelInSpace(Label label, int maxWidth){
        String[] words = label.getText().split("\\\\s+");
        List<String> wordList = new ArrayList<>();
        List<Integer> newlines = new ArrayList<>();

        for(int i = 0; i < words.length; i++){
            wordList.add(words[i]);
            rebuildLabel(label, wordList, newlines);
            if(label.getInternalWidth() > maxWidth){
                newlines.add(i - 1);
            }
        }
    }
    private static void rebuildLabel(Label label, List<String> words, List<Integer> newlines){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < words.size(); i++){
            builder.append(words.get(i));

            if(newlines.contains(i)){
                builder.append("\n");
            }else{
                builder.append(" ");
            }
        }

        label.setText(builder.toString());
    }

    private int     id;
    private Font    font;
    private String  text;

    private HorizontalAlignment horizontalAlignment;
    private VerticalAlignment   verticalAlignment;

    private int x;
    private int y;
    private int width;
    private int height;

    private byte r;
    private byte g;
    private byte b;
    private byte a;

    private Label(int id, Font font, String text,
                 HorizontalAlignment horizontalAlignment,
                 VerticalAlignment verticalAlignment,
                 int x, int y, int width, int height,
                 byte r, byte g, byte b, byte a){

        this.id   = id;
        this.font = font;
        this.text = text;

        this.horizontalAlignment = horizontalAlignment;
        this.verticalAlignment   = verticalAlignment;

        this.x      = x;
        this.y      = y;
        this.width  = width;
        this.height = height;

        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public int getId(){
        return id;
    }

    public void setFont(Font font){
        this.font = font;
        labelSetFont(id, font.getId());
        setDirty();
    }
    public Font getFont(){
        return font;
    }

    public void setText(String text){
        this.text = text;
        labelSetText(id, text);
        setDirty();
    }
    public String getText(){
        return text;
    }

    public void setHorizontalAlignment(HorizontalAlignment alignment){
        horizontalAlignment = alignment;
        setDirty();
    }
    public HorizontalAlignment getHorizontalAlignment(){
        return horizontalAlignment;
    }
    public void setVerticalAlignment(VerticalAlignment alignment){
        verticalAlignment = alignment;
        setDirty();
    }
    public VerticalAlignment getVerticalAlignment(){
        return verticalAlignment;
    }

    public void setColorR(byte r){
        this.r = r;
        labelSetColor(id, r, g, b, a);
        setDirty();
    }
    public void setColorG(byte g){
        this.g = g;
        labelSetColor(id, r, g, b, a);
        setDirty();
    }
    public void setColorB(byte b){
        this.b = b;
        labelSetColor(id, r, g, b, a);
        setDirty();
    }
    public void setColor(byte r, byte g, byte b){
        this.r = r;
        this.g = g;
        this.b = b;
        labelSetColor(id, r, g, b, a);
        setDirty();
    }
    public byte getColorR(){
        return r;
    }
    public byte getColorG(){
        return g;
    }
    public byte getColorB(){
        return b;
    }

    public int getInternalWidth(){
        return labelGetWidth(id);
    }
    public int getInternalHeight(){
        return labelGetHeight(id);
    }

    @Override
    public int getWidth(){
        return width;
    }
    @Override
    public int getHeight(){
        return height;
    }
    @Override
    public void setSize(int width, int height){
        this.width = width;
        this.height = height;
        labelSetWidth(id, width);
        setDirty();
    }
    @Override
    public void setWidth(int width){
        this.width = width;
        labelSetWidth(id, width);
        setDirty();
    }
    @Override
    public void setHeight(int height) {
        this.height = height;
        setDirty();
    }

    @Override
    public int getPositionX() {
        return x;
    }
    @Override
    public int getPositionY() {
        return y;
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        setDirty();
    }
    @Override
    public void setPositionX(int x) {
        this.x = x;
        setDirty();
    }
    @Override
    public void setPositionY(int y) {
        this.y = y;
        setDirty();
    }

    @Override
    public byte getAlpha() {
        return a;
    }
    @Override
    public void setAlpha(byte alpha) {
        a = alpha;
        labelSetAlpha(id, a);
        setDirty();
    }

    @Override
    public void draw() {
        int internalWidth   = labelGetWidth(id);
        int internalHeight  = labelGetHeight(id);

        int drawX = 0;
        int drawY = 0;

        switch(horizontalAlignment){
            case LEFT:{
                drawX = 0;
                break;
            }
            case CENTER:{
                drawX = (width / 2) - (internalWidth / 2);
                break;
            }
            case RIGHT:{
                drawX = width - internalWidth;
                break;
            }
        }
        switch(verticalAlignment){
            case TOP:{
                drawY = 0;
                break;
            }
            case CENTER:{
                drawY = (height / 2) - (internalHeight / 2);
                break;
            }
            case BOTTOM:{
                drawY = height - internalHeight;
                break;
            }
        }

        drawX += x;
        drawY += y;

        windowDrawLabel(id, drawX, drawY);
    }

    @Override
    public void dispose(){
        labelDestroy(id);
    }
}

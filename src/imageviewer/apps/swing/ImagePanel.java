package imageviewer.apps.swing;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel implements ImageDisplay {
    private BufferedImage bitmap;
    private BufferedImage bitmap2;
    private Image image;
    private Shift shift;
    private int offset;
    
    public ImagePanel(){
        MouseHandler mouserHandler = new MouseHandler();
        this.addMouseListener(mouserHandler);
        this.addMouseMotionListener(mouserHandler);
    }
    
    @Override
    public void paint(Graphics g){
        g.drawImage(bitmap,0,0,null);
    }
    
    @Override
    public void display(Image image){
        this.image = image;
        this.bitmap = loadBitmap(image);
        repaint();
    }
    
    private static BufferedImage loadBitmap(Image image){
        try{
            return ImageIO.read(new File(image.getName()));
        } catch (IOException ex) {
            return null;
        }
    }
    
    private Image imageAt (int shift){
        if (shift > 0)return this.shift.left();
        if (shift < 0)return this.shift.right();
        return null;
    }

    @Override
    public Image currentImage() {
        return image;
    }

    @Override
    public void on(Shift shift) {
        this.shift = shift;
    }

    private class MouseHandler implements MouseListener, MouseMotionListener {
        
        private int initial;

        public MouseHandler() {
        }

        @Override
        public void mouseClicked(MouseEvent event) {
        }

        @Override
        public void mousePressed(MouseEvent event) {
            this.initial = event.getX();
        }

        @Override
        public void mouseReleased(MouseEvent event) {
            int shift = shift(event.getX());
            if (Math.abs(shift) > getWidth()/2){
                image = imageAt(shift);
                bitmap = loadBitmap(image);
            }
            offset=0;
            repaint();
            
        }

        @Override
        public void mouseEntered(MouseEvent event) {
        }

        @Override
        public void mouseExited(MouseEvent event) {
        }

        @Override
        public void mouseDragged(MouseEvent event) {
            int shift = shift(event.getX());
            if (shift == 0) bitmap2= null;
            else if (offset/shift <= 0) bitmap2 = loadBitmap(imageAt(shift));
            offset = shift;
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent event) {
        }
        
        private int shift(int x){
            return x - initial;
        }
    }
    
    
}

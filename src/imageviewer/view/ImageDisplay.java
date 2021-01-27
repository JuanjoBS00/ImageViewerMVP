package imageviewer.view;

import imageviewer.model.Image;

public interface ImageDisplay {
    
    void display(Image image);
    public Image currentImage();
    
    void on(Shift shift);
    interface Shift{
        Image left();
        Image right();
    }
    
    
}

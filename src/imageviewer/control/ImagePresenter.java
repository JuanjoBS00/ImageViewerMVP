package imageviewer.control;

import java.util.List;
import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;


public class ImagePresenter {
    private final List<Image> images;
    private final ImageDisplay imageDisplay;

    public ImagePresenter(List<Image> images, ImageDisplay imageDisplay) {
        this.images = images;
        this.imageDisplay = imageDisplay;
        this.imageDisplay.on(shift());
    }
    
    private int current() {
        return images.indexOf(imageDisplay.currentImage());
    }

    private ImageDisplay.Shift shift() {
        return new ImageDisplay.Shift() {
            @Override
            public Image left() {
                int index = (current()+1) % images.size();
                return images.get(index);
            }

            @Override
            public Image right() {
                int index = (current()-1+images.size()) % images.size();
                return images.get(index);
            }

        };
    }

    

    
    
}

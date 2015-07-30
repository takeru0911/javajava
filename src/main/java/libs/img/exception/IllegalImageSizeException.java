package libs.img.exception;
public class IllegalImageSizeException extends ImageProcessingException{
    /**
     *
     */
    private static final long serialVersionUID = -1861482304476336296L;

    public IllegalImageSizeException(){
        super("illegal image size");
    }
}

package models;

public class ProductMedia {
    private int productMediaId;
    private int productId;
    private String mediaUrl;
    private String mediaType;
    private boolean isMain;

    public ProductMedia() {
    }

    public ProductMedia(int productMediaId, int productId, String mediaUrl, String mediaType, boolean isMain) {
        this.productMediaId = productMediaId;
        this.productId = productId;
        this.mediaUrl = mediaUrl;
        this.mediaType = mediaType;
        this.isMain = isMain;
    }

    public int getProductMediaId() {
        return productMediaId;
    }

    public void setProductMediaId(int productMediaId) {
        this.productMediaId = productMediaId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public boolean isMain() {
        return isMain;
    }

    public void setMain(boolean main) {
        isMain = main;
    }
}

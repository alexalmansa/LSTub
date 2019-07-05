import java.text.SimpleDateFormat;

/**
 * Classe que conté la informacio de un item en concret, un canal o un video
 *
 * @author Alex Almansa, Marc Llort
 *//*
    @param  likecount: integer que conté el numero de likes del video
    @param  dislikecount: integer que conté el numero de dislikes del video
    @param  title: string que conté el el titol del video
    @param  viewCount: integer que conté el numero de visites del video
    @param  subscriberCount: integer que conté el numero de subscriptors que te un canal

        */

public class statistics extends items {
    //videos
    private int likeCount;
    private int dislikeCount;
    private String title;
    private long viewCount;
    //canals
    private long subscriberCount;


    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title: string que conté el el titol del video
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return likeCount
     */
    public int getLikeCount() {
        return likeCount;
    }

    /**
     * @return dislikeCount
     */
    public int getDislikeCount() {
        return dislikeCount;
    }

    /**
     * @return relacio
     */
    public float getRelacio() {
        if (dislikeCount != 0) {
            return ((float) likeCount * 100 / (float) (likeCount + dislikeCount));
        } else {
            return 100;
        }
    }

    /**
     * @param likeCount: integer que conté el numero de likes del video
     */
    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * @param dislikeCount: integer que conte el numero de dislikes del video
     */
    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    /**
     * @return vewCount
     */
    public long getViewCount() {
        return viewCount;
    }

    /**
     * @param viewCount: integer que conté el numero de visites del video
     */
    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * @return subscriverCount
     */
    public long getSubscriberCount() {
        return subscriberCount;
    }

    /**
     * @param subscriberCount: integer que conté el numero de subscriptors que te un canal
     */
    public void setSubscriberCount(long subscriberCount) {
        this.subscriberCount = subscriberCount;
    }


}

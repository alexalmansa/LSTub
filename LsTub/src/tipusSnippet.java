import java.util.Date;
/**
 Classe que conté la informacio de un video o canal
 @author	Alex Almansa, Marc Llort
 *//*
 @param     publishedAt: conte la data de publicacio
 @param     title: conte el titol del video o canal
 @param     description: conté la descripcio del video o canal
 @param     thumbnails: conte la info del tamany del video
 @param     channelTitle: conte el titol del canal
 @param     resourceId: conte la info del id del element



 */

public class tipusSnippet extends items{

    protected Date publishedAt;

    protected String title;

    protected String description;

    protected qualitat thumbnails;

    protected String channelTitle;

    protected tipusId resourceId;

    /** @return publishedAt*/
    public Date getPublishedAt() {
        return publishedAt;
    }
    /** @return title*/
    public String getTitle() {
        return title;
    }
    /** @return description*/
    public String getDescription() {
        return description;
    }
    /** @return thumbnails*/
    public qualitat getThumbnails() {
        return thumbnails;
    }
    /** @return channelTitle*/
    public String getChannelTitle() {
        return channelTitle;
    }
    /** @return resourceId*/
    public tipusId getResourceId() {
        return resourceId;
    }
}

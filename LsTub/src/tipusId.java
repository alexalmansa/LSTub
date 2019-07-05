/**
 * Classe que conté la informacio de la id de un element
 *
 * @author Alex Almansa, Marc Llort
 *//*
 @param  videoId: conté la id del video si el element es un video
 @param  channelId: conté la id del canal si el element es un canal
 @param  playlistId: conté la id de la playlist si el element es una playlist


 */


public class tipusId extends items {

    private String videoId;

    private String channelId;

    private String playlistId;

    /**
     * @return videoId
     */
    public String getVideoId() {
        return videoId;
    }

    /**
     * @return channelId
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * @return playlistId
     */
    public String getPlaylistId() {
        return playlistId;
    }
}

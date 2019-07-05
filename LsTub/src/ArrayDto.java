import java.util.ArrayList;

/**
 * Classe que conté tota la informacio de la cerca
 *
 * @author Alex Almansa, Marc Llort
 *//*
 @param items: array de items
 @param nextPageToken: id de la seguent pagina
 @param prevPageToken: id de la anterior pagina

 */
public class ArrayDto extends YoutubeData {

    private ArrayList<items> items = new ArrayList<items>();

    private String prevPageToken;

    private String nextPageToken;

    /**
     * @return items
     */

    public ArrayList<items> getItems() {
        return items;
    }

    /**
     * @return prevPageToken
     */

    public String getPrevPageToken() {
        return prevPageToken;
    }

    /**
     * @return nextPageToken
     */

    public String getNextPageToken() {
        return nextPageToken;
    }

}

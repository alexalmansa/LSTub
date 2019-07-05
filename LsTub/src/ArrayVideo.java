import java.util.ArrayList;

/**
 * Classe que conté la informacio del videos , canals i playlistrs
 *
 * @author Alex Almansa, Marc Llort
 *//*
 @param tipusPage: results per page i totalresults d'una playlist:
 @param items: array de tots els items


 */
public class ArrayVideo extends YoutubeData {

    private tipusPage pageInfo;

    private ArrayList<Estadistiques> items = new ArrayList<Estadistiques>();


    /**
     * @return items
     */
    public ArrayList<Estadistiques> getItems() {

        return items;
    }


    /**
     * @return pageInfo
     */
    public tipusPage getPageInfo() {
        return pageInfo;
    }
}

/**
 * Classe que conté la informacio de la qualitat de un item
 *
 * @author Alex Almansa, Marc Llort
 *//*
 @param  standard: conte la informacio del item de qualitat standard
 @param  high: conte la informacio del item de qualitat high


 */
public class qualitat extends tipusSnippet {

    protected tipusThumbnail standard;
    protected tipusThumbnail high;

    /**
     * @return standard
     */
    public tipusThumbnail getStandard() {
        return standard;
    }

    /**
     * @return high
     */
    public tipusThumbnail getHigh() {
        return high;
    }
}

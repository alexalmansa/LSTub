import java.util.ArrayList;
/**
 Classe que conté la informacio de cada un dels items de la cerca
 @author	Alex Almansa, Marc Llort
 *//*
 @param tipusId: id: conte la informacio de la id
 @param snippet: conte la informacio de la llista d'elements


 */
public class items extends ArrayDto{

    //Resultats JSON

    protected tipusId id;

    protected tipusSnippet snippet;
    /** @return id*/
    public tipusId getId() {
        return id;
    }
    /** @return snippet*/
    public tipusSnippet getSnippet() {
        return snippet;
    }
}

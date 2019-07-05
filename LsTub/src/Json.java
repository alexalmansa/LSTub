import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

/**
 Funcio que s'encarrega de tractar la informacio del Json llegint i escribint-lo de nou
 @author	Alex Almansa, Marc Llort
 *//*
 @return htmlBuilder: String on hi ha tot el html

 */
public class Json {
    private String paginaAnterior;

    private String paginaSeguent;

    /**
     Funcio que s'encarrega de realitzar la cerca i ens retorna el JsonObject amb els resultats de la mateixa
     @author	Alex Almansa, Marc Llort
     @param cerca: String on hi ha la cerca que es vol realitzar
     @param pagina: Int que ens indica si volem avançar o retrocedir de pagina durant la cerca
     @return dto: variable de tipus ArrayDto on hi ha la informacio dels resultats de la cerca

     */
    public ArrayDto creaJsonObject(String cerca, int pagina)  {

        String urrl = new String();


        switch (pagina) {
            case 0:
                urrl = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=" + cerca + "&maxResults=10&key=AIzaSyCMuiLYiRxgSu0Z8zorZ0lRrwbN8c4_MKc";
                break;

            case -2:
                urrl = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=" + cerca + "&pageToken=" + paginaAnterior + "&maxResults=10&key=AIzaSyCMuiLYiRxgSu0Z8zorZ0lRrwbN8c4_MKc";
                break;

            case -1:
                urrl = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=" + cerca + "&pageToken=" + paginaSeguent + "&maxResults=10&key=AIzaSyCMuiLYiRxgSu0Z8zorZ0lRrwbN8c4_MKc";
                break;
        }
        try {
            URL url = new URL(urrl);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            ArrayDto dto = new ArrayDto();
            dto = new Gson().fromJson(reader, ArrayDto.class);

            paginaSeguent = dto.getNextPageToken();
            paginaAnterior = dto.getPrevPageToken();
            return dto;
        }catch(Exception e){
            System.out.println("No hi ha internet!");
            return null;
        }

    }               //Funcio crea Json Object

    /**
     Funcio que s'encarrega de crear el fitxer favoritplaces.json on hi haura els videos, canals i playlists que guardem a faborits
     @author	Alex Almansa, Marc Llort
     @param opcio: posicio que es troba el video que l'usuari preten guardar
     @param dto: array de els diferents elements resultants de la cerca

     */
    public void jsonCreator(int opcio, ArrayDto dto) {

        try {
            Gson gson = new Gson();
            Gson gson2 = new Gson();
            ArrayDto json = new ArrayDto();
            String jsonString2 = new String();

            json = gsonLector();    //Llegim el json que hi ha actualment

            if (json != null) {

                for (int i = 0; i < json.getItems().size(); i++){
                    if(i == 0 ){
                        jsonString2 = gson2.toJson(json.getItems().get(0));     // el posem tot a un string
                    }
                    else{
                        jsonString2 = jsonString2 + ",\n" + gson2.toJson(json.getItems().get(i));     // Afegim el nou element a guardar a l'string
                    }
                }
            }

            String jsonString = gson.toJson(dto.getItems().get(opcio - 1));                                 // Escribim el fitxer
            FileWriter fileWriter = new FileWriter("favoritePlaces.json");
            fileWriter.write("{\"items\": [" + jsonString + "," + jsonString2 + "]}");                 // cal afegir davant:       {"items": [            i darrere:       ]}
            fileWriter.close();

        }
        catch (IOException e) {
            System.out.println("Error al escriure el json");
        }

    }

    /**
     Funcio que s'encarrega de llegir el json que tenim actualmenta  la carpeta
     @author	Alex Almansa, Marc Llort
     @return json: arrayDto on hi ha tots els elements que ja es troben guardats com a faborits a faboriteplaces.json

     */
    public ArrayDto gsonLector() {

        Gson gson = new Gson();
        JsonReader reader;
        ArrayDto json = new ArrayDto();

        try {
            reader = new JsonReader(new FileReader("favoritePlaces.json"));
            json = gson.fromJson(reader, ArrayDto.class);

        } catch (IOException e) {
            System.out.println("Error! No s'ha trobat el fitxer!");
        }catch (JsonSyntaxException eu){
            System.out.println("Error! Format del json mal definit");
        }

        return json;
    }


    /**
     Funcio que s'encarrega de llegir quin es el video que es vol guardar a faboritos o si es vol avançar o retrocedir de pagina
     @author	Alex Almansa, Marc Llort
     @param  error: Variable que decideix si cal printar o no les opcions de passsar pagina i tornar al menu
     @return pagina: integer que ens mostra la posicio del video que s'ha volgut guardar o -1,-2 o -3 si es vol avançar, retrocedir de pagina o tornar al menu

     */
    public int llegeixOpcio(Boolean error) {

        int pagina = 0;
        String text;
        boolean opcioIncorrecta = false;

        //Printem Opcions



        if(!error) {
            System.out.println("    NEXT : pagina seguent");
            if (paginaAnterior != null) {
                System.out.println("    BACK : pagina anterior");
            }
            System.out.println("    STOP : torna al menu");
            System.out.println("\nTria una opció:");
        }
        else{pagina = -3;}
        while (!opcioIncorrecta && !error) {                                //controlo que en cas de 6 acabi el programa, de 1 a 5 agafi les opcions, i altres numeros siguin error

            Scanner sc1 = new Scanner(System.in);

            if (sc1.hasNextInt()) {                                //si es un int, agafem pagina

                pagina = sc1.nextInt();

                if (0 >= pagina || pagina > 10) {
                    System.out.println("Error! La selecció ha de ser un nombre entre 1 i 10!");
                }
                else {
                    opcioIncorrecta = true;
                }
            } else {                                                //En cas que no sigui un int
                text = sc1.next();


                if (text.compareToIgnoreCase("Next") == 0) {       //Mirem si es next i asignem -1 a la var que controla el canvi de pagina
                    pagina = -1;
                    opcioIncorrecta = true;
                }
                if (paginaAnterior != null && text.compareToIgnoreCase("Back") == 0) {       //Mirem si es back i asignem -2 a la var que controla el canvi de pagina
                    pagina = -2;
                    opcioIncorrecta = true;
                }
                if (text.compareToIgnoreCase("Stop") == 0) {                    //Mirem si es stop i assignem un valor perque pari de mostrar el menú
                    pagina = -3;
                    opcioIncorrecta = true;
                }
            }

            if (!opcioIncorrecta) {         //En cas de que no entri a cap if, vol dir que no ens han entrat correctrament el text
                System.out.println("Error, opcio incorrecta!");
            }
        }

        return pagina;
    }                                       //Funcio llegeix opcio, mostra opcions d'avançar i retrocedir pagina i retorna pagina a creajsonobject amb un valor per retrocedir, avançar i parar



}//Del json Class

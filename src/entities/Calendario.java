package entities;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Calendario {
    private static final Map<Date, String> feriados = new HashMap<>();
    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    private final String file = "holidays.xml";
    private String cidade;
    private String estado;
    private String ano;

    public Calendario(String cidade, String estado, String ano) {
        this.cidade = cidade;
        this.estado = estado;
        this.ano = ano;
    }

    private void requestFeriado() throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.calendario.com.br/?&ano=" + ano + "&estado=" + estado
                        + "&cidade=" + cidade + "&token=Z3Vzd"
                        + "GF2b2JhdG9yYXhAZ21haWwuY29tJmhhc2g9MjAyODA4OTk3"))
                .build();

        client.send(request, HttpResponse.BodyHandlers.ofFile(Paths.get(file)));
    }

    private void readNodeList(NodeList nodeList) throws ParseException {
        for (int count = 0; count < nodeList.getLength(); count++) {
            Node elemNode = nodeList.item(count);
            if (elemNode.getNodeType() == Node.ELEMENT_NODE) {

                if(elemNode.getNodeName().equals("date"))
                    feriados.put(sdf1.parse(elemNode.getTextContent()),null);

                if(elemNode.getNodeName().equals("type"))
                    feriados.forEach((k,v) -> {
                        if (v == null) {
                            feriados.put(k, elemNode.getTextContent());
                        }
                    });

                if (elemNode.hasAttributes()) {
                    NamedNodeMap nodeMap = elemNode.getAttributes();
                    for (int i = 0; i < nodeMap.getLength(); i++) {
                        Node node = nodeMap.item(i);
                    }
                }
                if (elemNode.hasChildNodes()) {
                    //recursive call if the node has child nodes
                    readNodeList(elemNode.getChildNodes());
                }
            }
        }
    }

    public Map<Date, String> getFeriados() throws IOException, InterruptedException {
        requestFeriado();

        try {
            File arquivoXML = new File(file);
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(arquivoXML);
            if (document.hasChildNodes()) {
                readNodeList(document.getChildNodes());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return feriados;
    }
}

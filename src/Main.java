import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the ID of the book you want to extract information: ");
            String bookID = scanner.nextLine();
            scanner.close();
            File inputFile = new File("src/books.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
          //  System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            //read array of books element
            NodeList nodeList = doc.getElementsByTagName("book");
            boolean found = false;
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
             //   System.out.println("node name =  " + node.getNodeName()+ ""+ (i+1));
                if(node.getNodeType()==Node.ELEMENT_NODE){
                    Element eElement =(Element) node;
                    if (eElement.getAttribute("id").equals(bookID)) {
                        System.out.printf("Book ID: %s%nAuthor: %s%nTitle: %s%nGenre: %s%nPrice: %s%nPublish Date: %s%nDescription: %s%n",
                                eElement.getAttribute("id"),
                                eElement.getElementsByTagName("author").item(0).getTextContent(),
                                eElement.getElementsByTagName("title").item(0).getTextContent(),
                                eElement.getElementsByTagName("genre").item(0).getTextContent(),
                                eElement.getElementsByTagName("price").item(0).getTextContent(),
                                eElement.getElementsByTagName("publish_date").item(0).getTextContent(),
                                eElement.getElementsByTagName("description").item(0).getTextContent());
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                System.out.println("Book ID '" + bookID + "' not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

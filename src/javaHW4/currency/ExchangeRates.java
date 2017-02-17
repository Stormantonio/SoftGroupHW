package javaHW4.currency;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton on 15.02.2017.
 * Найти курс доллара на сайте (сайт любой) и вывести его на консоль
 */
public class ExchangeRates {
    private static String line;

    public static void main(String[] args) throws IOException {
        ExchangeRates rates = new ExchangeRates();
        while (true) {
            System.out.println("Выберите валюту: USD | EUR | RUB | '1' more currency | '2' full information about currency | '0' for exit");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            line = reader.readLine().trim().toUpperCase();
            switch (line) {
                case "0":
                    return;
                default:
                    rates.getXml();
                    rates.parsing();
            }
        }
    }

    private void getXml() {
        String input;
        URL url = null;
        try {
            url = new URL("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        File currency = new File("src" + getSeparator() + "javaHW4" + getSeparator() + "currency" + getSeparator() + "currency.xml");
        FileWriter writer;
        try {
            writer = new FileWriter(currency);
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(connect.getInputStream());
            BufferedReader br = new BufferedReader(in);
            while ((input = br.readLine()) != null) {
                writer.write(input);
            }
            br.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getSeparator() {
        return System.getProperty("file.separator");
    }

    private void parsing() {
        File file = new File("src" + getSeparator() + "javaHW4" + getSeparator() + "currency" + getSeparator() + "currency.xml");

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            NodeList currencyList = document.getElementsByTagName("currency");
            List<Money> moneyList = new ArrayList<>();
            for (int i = 0; i < currencyList.getLength(); i++) {
                if (currencyList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element moneyElement = (Element) currencyList.item(i);
                    Money money = new Money();
                    NodeList childNodes = moneyElement.getChildNodes();
                    for (int j = 0; j < childNodes.getLength(); j++) {
                        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            Element childElement = (Element) childNodes.item(j);
                            switch (childElement.getNodeName()) {
                                case "r030":
                                    break;
                                case "txt":
                                    money.setCurrencyName(childElement.getTextContent());
                                    break;
                                case "rate":
                                    money.setCourse(Double.parseDouble(childElement.getTextContent()));
                                    break;
                                case "cc":
                                    money.setStringCode(childElement.getTextContent());
                                    break;
                                case "exchangedate":
                                    money.setExchangeDate(childElement.getTextContent());
                            }
                        }
                    }
                    moneyList.add(money);
                }
            }
            if (line.equals("1")) {
                for (Money m : moneyList) {
                    System.out.println("Код літерний: " + m.getStringCode() + " (" + m.getCurrencyName() + ")");
                }
                System.out.println();
                return;
            }
            if (line.equals("2")) {
                for (Money m : moneyList) {
                    System.out.println(m);
                }
                System.out.println();
            } else {
                for (Money m : moneyList) {
                    if (m.getStringCode().equals(line)) {
                        System.out.println(m + "\n");
                        return;
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}

class Money {
    private String currencyName;
    private Double course;
    private String stringCode;
    private String exchangeDate;

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public void setCourse(Double course) {
        this.course = course;
    }

    public String getStringCode() {
        return stringCode;
    }

    public void setStringCode(String stringCode) {
        this.stringCode = stringCode;
    }

    public void setExchangeDate(String exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    @Override
    public String toString() {
        return "Валюта: " + currencyName + " | Код літерний: " + stringCode
                + " | Офіційний курс: " + course + " | Курс на поточну дату: " + exchangeDate;
    }
}

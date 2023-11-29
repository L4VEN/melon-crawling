package basic;

import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("[ ��� ��Ʈ ��ȸ �� �˻� ]");

        while (true) {
        	System.out.print("'�˻�' Ȥ�� '��Ʈ' �Է� (0 �Է½� ����) : ");
            String search = sc.next();
            if (search.equals("0")) {
                break;
            }
            String url = "https://www.melon.com/chart/index.htm";
            Document doc = null;

            if (search.equals("��Ʈ")) {
                url = "https://www.melon.com/chart/index.htm";

                try {
                    doc = Jsoup.connect(url).get();

                    Elements elements = doc.select("tbody");

                    Elements titles = elements.select(".rank01 a");
                    Elements artists = elements.select(".rank02 a:nth-child(1)");

                    String[] titleArr = titles.eachText().toArray(new String[0]);
                    String[] artistArr = artists.eachText().toArray(new String[0]);
                    for (int i = 0; i < titleArr.length; i++) {
                        System.out.println((i + 1) + "  " + titleArr[i] + " - " + artistArr[i * 2]);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (search.equals("�˻�")) {
                url = "https://www.melon.com/search/song/index.htm?q=";
                System.out.print("�˻��ϰ� ���� ��Ƽ��Ʈ : ");
                String artistName = sc.next();
                url += artistName + "&section=artist";

                try {
                    doc = Jsoup.connect(url).get();

                    Elements elements = doc.select("tbody");

                    Elements titles = elements.select(".ellipsis .fc_gray");
                    String[] titleArr = titles.eachText().toArray(new String[0]);

                    if(titleArr.length == 0) {
                    	System.out.println("�˻� ����� �����ϴ�.");
                    	continue;
                    }
                    for (int i = 0; i < titleArr.length; i++) {
                    	if(i == 15) break;
                    	else if (!titleArr[i].toLowerCase().contains("inst")) {
                            System.out.println((i + 1) + "  " + titleArr[i]);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("�ùٸ� ����� �Է��ϼ���.");
            }


            System.out.println();
        }
        System.out.println("[ ���� ]");
        sc.close();
    }
}
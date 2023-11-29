package basic;

import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("[ 멜론 차트 조회 및 검색 ]");

        while (true) {
        	System.out.print("'검색' 혹은 '차트' 입력 (0 입력시 종료) : ");
            String search = sc.next();
            if (search.equals("0")) {
                break;
            }
            String url = "https://www.melon.com/chart/index.htm";
            Document doc = null;

            if (search.equals("차트")) {
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
            else if (search.equals("검색")) {
                url = "https://www.melon.com/search/song/index.htm?q=";
                System.out.print("검색하고 싶은 아티스트 : ");
                String artistName = sc.next();
                url += artistName + "&section=artist";

                try {
                    doc = Jsoup.connect(url).get();

                    Elements elements = doc.select("tbody");

                    Elements titles = elements.select(".ellipsis .fc_gray");
                    String[] titleArr = titles.eachText().toArray(new String[0]);

                    if(titleArr.length == 0) {
                    	System.out.println("검색 결과가 없습니다.");
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
                System.out.println("올바른 명령을 입력하세요.");
            }


            System.out.println();
        }
        System.out.println("[ 종료 ]");
        sc.close();
    }
}
package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class wuo_report {
    public static void main( String[] args ) throws Exception
    {
        for (int i = 0; i < 10; i++) {
        int page = i * 25;  // 製造分頁引數
        Document document = Jsoup.connect("https://wuo-wuo.com/report?start="+ page).get();

        Elements items = document.getElementsByClass("item");  // 批量獲取當前頁中class=item的元素
            for (org.jsoup.nodes.Element item : items) {  // 每個item中都包含當前的所有資料

                //圖片連結
                Elements imgs = item.getElementsByClass("image-intro").select("img");
                for(org.jsoup.nodes.Element img:imgs) {
                    String imgUrl = img.attr("src");

                    System.out.println("https://wuo-wuo.com"+imgUrl);
                }

                //文章連結
                Elements articles = item.getElementsByClass("article-title").select("a");
                for(org.jsoup.nodes.Element article:articles) {
                    String articleUrl = article.attr("href");

                    System.out.println("https://wuo-wuo.com"+articleUrl);
                }
                
                String title = item.getElementsByClass("article-header clearfix").get(0).getElementsByTag("a").get(0).text();
                String author = item.getElementsByClass("createdby hasTooltip").get(0).getElementsByTag("span").get(0).text();
                String date = item.getElementsByClass("published hasTooltip").get(0).getElementsByTag("time").get(0).text();
                String pfm = "窩窩";
                

                System.out.println("平台: " + pfm);
                System.out.println("標題: " + title);
                System.out.println("作者: " + author);
                System.out.println("發佈時間: " + date);
                System.out.println("===================================");
            }
        }
    }
}

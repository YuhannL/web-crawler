package com.example;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class wuo_topic {
    public static void main( String[] args ) throws IOException
    {
        
        for (int i = 0; i < 40; i++) {
        int page = i * 9;  // 製造分頁引數
        Document document = Jsoup.connect("https://wuo-wuo.com/topics?start="+ page).get();

        Elements items = document.getElementsByClass("item");  // 批量獲取當前頁中class=item的元素
            for (org.jsoup.nodes.Element item : items) {  // 每個item中都包含當前的所有資料
                
                //圖片連結
                Elements imgs = item.getElementsByClass("image-intro").select("img");
                for(org.jsoup.nodes.Element img:imgs) {
                    String imgUrl = img.attr("src");

                    System.out.println("https://wuo-wuo.com"+imgUrl);
                    break;
                }

                //文章連結
                Elements articels = item.getElementsByClass("article-title").select("a");
                for(org.jsoup.nodes.Element article:articels) {
                    String articleUrl = article.attr("href");

                    Document doc = Jsoup.connect("https://wuo-wuo.com"+ articleUrl).get();

                    Elements artis = doc.getElementsByClass("article-info  muted");
                    for (org.jsoup.nodes.Element auth : artis){
                        String author = "";
                        if (auth.getElementsByClass("createdby hasTooltip").size() > 0) {
                            author = auth.getElementsByClass("createdby hasTooltip").get(0).getElementsByTag("span").get(0).text();
                        }
                        System.out.println("作者: "+ author);
                        break;
                    }
                    
                    System.out.println("https://wuo-wuo.com"+articleUrl);
                    break;
                }

                String title = item.getElementsByClass("article-header clearfix").get(0).getElementsByTag("a").get(0).text();
                String date = item.getElementsByClass("published hasTooltip").get(0).getElementsByTag("time").get(0).text();

                System.out.println("標題: " + title);
                System.out.println("發佈時間: " + date);
                System.out.println("===================================");
            }
        }
    }
}





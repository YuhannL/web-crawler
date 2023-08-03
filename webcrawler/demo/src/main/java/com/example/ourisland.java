package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ourisland {
    public static void main(String[] args) throws Exception {
        for (int i = 1; i < 10; i++) {
            int page = i * 50; // 製造分頁引數
            Document document = Jsoup.connect("https://ourisland.pts.org.tw/%E5%AD%B8%E7%A7%91/%E5%8B%95%E7%89%A9?page=" + page).get();
            Elements contents = document.getElementsByClass("view"); // 批量獲取當前頁中class=item的元素
            for (org.jsoup.nodes.Element content : contents) { // 每個item中都包含當前的所有資料
                // 圖片連結
                Elements imgs = content.getElementsByClass("views-row-inner").select("img");
                for (org.jsoup.nodes.Element img : imgs) {
                    String imgUrl = img.attr("href");

                    System.out.println("https://ourisland.pts.org.tw/sites/default/files/styles/report_list_2018/public/field/image/" + imgUrl);
                }

                // 文章連結
                Elements titles = content.getElementsByClass("view-list-title").select("a");
                for (org.jsoup.nodes.Element title : titles) {
                    String titleUrl = title.attr("href");

                    Document doc = Jsoup.connect("https://ourisland.pts.org.tw/" + titleUrl).get();
                    Elements artis = doc.getElementsByClass("content-panel");
                    for (org.jsoup.nodes.Element auth : artis) {
                        String titl = auth.getElementsByClass("content-header").get(0).getElementsByClass("page-title").get(0).text();
                        String date = auth.getElementsByClass("node-header").get(0).getElementsByClass("submitted").get(0).text();
                        String author = "";
                        if (auth.getElementsByClass("field-content").size() > 0) {
                            author = auth.getElementsByClass("field-content").get(0).getElementsByTag("span")
                                    .get(0).text();
                        }
                        System.out.println("標題: " + titl);
                        System.out.println("發佈時間: " + date);
                        System.out.println("作者: " + author);
                        
                    }
                    System.out.println("https://ourisland.pts.org.tw/" + titleUrl);
                    
                }
                System.out.println("===================================");
            }
        }
    }
}

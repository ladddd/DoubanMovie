package com.ladddd.douban.bean

/**
 * Created by 陈伟达 on 2017/12/6.
 * alt: "https://movie.douban.com/subject/1292052/"
 * casts: [{alt: "https://movie.douban.com/celebrity/1054521/",…},…]
 * collect_count: 1164942
 * directors: [{alt: "https://movie.douban.com/celebrity/1047973/",…}]
 * genres: ["犯罪", "剧情"]
 * id: "1292052"
 * images: {small: "http://img7.doubanio.com/view/photo/s_ratio_poster/public/p480747492.jpg",…}
 * original_title: "The Shawshank Redemption"
 * rating: {max: 10, average: 9.6, stars: "50", min: 0}
 * subtype: "movie"
 * title: "肖申克的救赎"
 * year: "1994"
 *
 */
data class MovieSubject(val id:String, val title:String, val original_title:String, val alt:String,
                        val images:SimpleImage, val rating:SimpleRating, val pubdates:List<String>, val year:String,
                        val subtype:String, val genres:List<String>, val collect_count:Long,
                        val directors:List<SimpleCelebrity>, val casts:List<SimpleCelebrity>)
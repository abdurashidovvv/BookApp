package com.example.bookapp.data.models.modelBookByCategory

import java.io.Serializable

data class Book(
    val age_group: String,
    val amazon_product_url: String,
    val article_chapter_link: String,
    val asterisk: Int,
    val author: String,
    val book_image: String,
    val book_image_height: Int,
    val book_image_width: Int,
    val book_review_link: String,
    val book_uri: String,
    val buy_links: List<BuyLink>,
    val contributor: String,
    val contributor_note: String,
    val dagger: Int,
    val description: String,
    val first_chapter_link: String,
    val isbns: List<Isbn>,
    val price: String,
    val primary_isbn10: String,
    val primary_isbn13: String,
    val publisher: String,
    val rank: Int,
    val rank_last_week: Int,
    val sunday_review_link: String,
    val title: String,
    val weeks_on_list: Int
):Serializable
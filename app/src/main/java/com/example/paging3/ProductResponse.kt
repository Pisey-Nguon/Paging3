package com.example.paging3

data class ProductResponse(
    val data: List<ProductData>,
    val meta: Meta,
)

data class ProductData(
    val id: Long,
    val image: String,
    val name: String,
    val qtyOnHand: Long,
    val stockStatus: String,
    val detail: String,
    val media: List<String?>,
    val totalView: Long,
    val totalSale: Long,
    val averageRate: Long,
    val totalVariant: Long,
    val store: Store,
    val video: String,
    val totalRating: Long,
    val brand: Brand?,
    val totalReview: Long,
    val isWishlist: Boolean,
    val category: Category,
    val prices: Prices,
)

data class Store(
    val id: Long,
    val logo: String,
    val name: String,
    val cover: String,
    val phone: String,
    val totalFollower: Long,
    val totalRate: Long,
    val averageRate: Long,
    val address: String,
    val description: Any?,
)

data class Brand(
    val id: Long,
    val image: String,
    val name: String,
)

data class Category(
    val id: Long,
    val image: Any?,
    val name: String,
    val mainCategoryId: Long,
)

data class Prices(
    val priceBeforeDiscount: Double,
    val discountPercentage: Long,
    val discountFixedAmount: Long,
    val discountAmount: Long,
    val priceAfterDiscount: Double,
    val disPercentage: Long,
)

data class Meta(
    val limit: Long,
    val offset: Long,
    val total: Long,
)


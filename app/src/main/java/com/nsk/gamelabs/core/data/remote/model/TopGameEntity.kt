package com.nsk.gamelabs.core.data.remote.model

data class TopGameEntity(
    val count: Long,
    val next: String,
    val previous: Any?,
    val results: List<Result>,
    val seo_title: String,
    val seo_description: String,
    val seo_keywords: String,
    val seo_h1: String,
    val noindex: Boolean,
    val nofollow: Boolean,
    val description: String,
    val filters: Filters,
    val nofollow_collections: List<String>,
) {
    data class Result(
        val id: Long,
        val slug: String,
        val name: String,
        val released: String,
        val tba: Boolean,
        val background_image: String,
        val rating: Double,
        val rating_top: Long,
        val ratings: List<Rating>,
        val ratings_count: Long,
        val reviews_text_count: Long,
        val added: Long,
        val added_by_status: AddedByStatus,
        val metacritic: Long,
        val playtime: Long,
        val suggestions_count: Long,
        val updated: String,
        val user_game: Any?,
        val reviews_count: Long,
        val saturated_color: String,
        val dominant_color: String,
        val platforms: List<Platform>,
        val parent_platforms: List<ParentPlatform>,
        val genres: List<Genre>,
        val stores: List<Store>,
        val clip: Any?,
        val tags: List<Tag>,
        val esrb_rating: EsrbRating,
        val short_screenshots: List<ShortScreenshot>,
    ) {
        data class Rating(
            val id: Long,
            val title: String,
            val count: Long,
            val percent: Double,
        )

        data class AddedByStatus(
            val yet: Long,
            val owned: Long,
            val beaten: Long,
            val toplay: Long,
            val dropped: Long,
            val playing: Long,
        )

        data class Platform(
            val platform: PlatformInfo,
            val released_at: String,
            val requirements_en: RequirementsEn?,
            val requirements_ru: RequirementsRu?,
        ) {
            data class PlatformInfo(
                val id: Long,
                val name: String,
                val slug: String,
                val image: Any?,
                val year_end: Any?,
                val year_start: Long?,
                val games_count: Long,
                val image_background: String,
            )

            data class RequirementsEn(
                val minimum: String,
                val recommended: String?,
            )

            data class RequirementsRu(
                val minimum: String,
                val recommended: String,
            )
        }

        data class ParentPlatform(
            val platform: ParentPlatformInfo,
        ) {
            data class ParentPlatformInfo(
                val id: Long,
                val name: String,
                val slug: String,
            )
        }

        data class Genre(
            val id: Long,
            val name: String,
            val slug: String,
            val games_count: Long,
            val image_background: String,
        )

        data class Store(
            val id: Long,
            val store: StoreInfo,
        ) {
            data class StoreInfo(
                val id: Long,
                val name: String,
                val slug: String,
                val domain: String,
                val games_count: Long,
                val image_background: String,
            )
        }

        data class Tag(
            val id: Long,
            val name: String,
            val slug: String,
            val language: String,
            val games_count: Long,
            val image_background: String,
        )

        data class EsrbRating(
            val id: Long,
            val name: String,
            val slug: String,
        )

        data class ShortScreenshot(
            val id: Long,
            val image: String,
        )
    }

    data class Filters(
        val years: List<Year>,
    ) {
        data class Year(
            val from: Long,
            val to: Long,
            val filter: String,
            val decade: Long,
            val years: List<YearDetail>,
            val nofollow: Boolean,
            val count: Long,
        ) {
            data class YearDetail(
                val year: Long,
                val count: Long,
                val nofollow: Boolean,
            )
        }
    }
}

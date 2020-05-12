package com.mercadolibre.android.andesui.tag.factory.simple

import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.tag.leftcontent.LeftContent
import com.mercadolibre.android.andesui.tag.rightcontent.RightContent
import com.mercadolibre.android.andesui.tag.rightcontent.RightContentDismiss
import com.mercadolibre.android.andesui.tag.size.AndesTagSize
import com.mercadolibre.android.andesui.tag.type.AndesTagType

/**
 * The data class that contains the public components of the tag.
 */
internal data class AndesTagSimpleAttrs(
    val andesTagType: AndesTagType,
    val andesTagSize: AndesTagSize,
    val andesSimpleTagText: String?,
    val leftContent: LeftContent? = null,
    val rightContent: RightContent? = null
)

/**
 * This object parse the attribute set and return an instance of AndesSimpleTagAttrs to be used by AndesSimpleTag
 */
internal object AndesTagSimpleAttrsParser {

    private const val ANDES_SIMPLE_TAG_TYPE_NEUTRAL = "2000"
    private const val ANDES_SIMPLE_TAG_TYPE_HIGHLIGHT = "2001"
    private const val ANDES_SIMPLE_TAG_TYPE_SUCCESS = "2002"
    private const val ANDES_SIMPLE_TAG_TYPE_WARNING = "2003"
    private const val ANDES_SIMPLE_TAG_TYPE_ERROR = "2004"

    private const val ANDES_TAG_SIZE_LARGE = "3000"
    private const val ANDES_TAG_SIZE_SMALL = "3001"

    private const val ANDES_TAG_DISMISSABLE = "4000"
    private const val ANDES_TAG_NOT_DISMISSABLE = "4001"

    fun parse(context: Context, attr: AttributeSet?): AndesTagSimpleAttrs {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesTagSimple)

        val type = when (typedArray.getString(R.styleable.AndesTagSimple_tagSimpleType)) {
            ANDES_SIMPLE_TAG_TYPE_NEUTRAL -> AndesTagType.NEUTRAL
            ANDES_SIMPLE_TAG_TYPE_HIGHLIGHT -> AndesTagType.HIGHLIGHT
            ANDES_SIMPLE_TAG_TYPE_SUCCESS -> AndesTagType.SUCCESS
            ANDES_SIMPLE_TAG_TYPE_WARNING -> AndesTagType.WARNING
            ANDES_SIMPLE_TAG_TYPE_ERROR -> AndesTagType.ERROR
            else -> AndesTagType.DEFAULT
        }

        val size = when (typedArray.getString(R.styleable.AndesTagSimple_tagSimpleSize)) {
            ANDES_TAG_SIZE_LARGE -> AndesTagSize.LARGE
            ANDES_TAG_SIZE_SMALL -> AndesTagSize.SMALL
            else -> AndesTagSize.LARGE
        }

        val isDismissable = when (typedArray.getString(R.styleable.AndesTagSimple_tagSimpleIsDismissable)) {
            ANDES_TAG_DISMISSABLE -> RightContentDismiss(context)
            ANDES_TAG_NOT_DISMISSABLE -> RightContent(context)
            else -> RightContent(context)
        }

        return AndesTagSimpleAttrs(
                andesTagType = type,
                andesTagSize = size,
                andesSimpleTagText = typedArray.getString(R.styleable.AndesTagSimple_tagSimpleText),
                rightContent = isDismissable
        ).also { typedArray.recycle() }
    }
}

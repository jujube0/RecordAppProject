package com.gyoung.movierecord.etc

import android.graphics.Canvas
import android.graphics.Paint
import android.text.Layout
import android.text.style.LeadingMarginSpan

class MyLeadingMarginSpan2(val lines : Int, val margin : Int) : LeadingMarginSpan.LeadingMarginSpan2 {
    override fun drawLeadingMargin(
        c: Canvas?,
        p: Paint,
        x: Int,
        p3: Int,
        p4: Int,
        p5: Int,
        p6: Int,
        p7: CharSequence?,
        p8: Int,
        p9: Int,
        p10: Boolean,
        p11: Layout?
    ) {
    }

    override fun getLeadingMarginLineCount(): Int {
        return lines
    }

    override fun getLeadingMargin(first: Boolean): Int {
        if(first){
            return margin
        }else{
            return 0
        }
    }
}
package com.soywiz.klock

import kotlin.test.*

class DateTimeTest {
    val HttpDate = DateFormat("EEE, dd MMM yyyy HH:mm:ss z")
    val HttpDate2 = DateFormat("EEE, dd MMM yyyy H:mm:ss z")

    @Test
    fun testFromString() {
        assertEquals("Mon, 04 Dec 2017 04:35:37 UTC", DateTime.fromString("2017-12-04T04:35:37Z").toString())
    }

    @Test
    fun testFormattingToCustomDateTimeFormats() {
        val dt = DateTime(2018, 9, 8, 4, 8, 9)
        assertEquals("Sat, 08 Sep 2018 04:08:09 UTC", dt.format("EEE, dd MMM yyyy HH:mm:ss z"))
        assertEquals("Saturday, 08 Sep 2018 04:08:09 UTC", dt.format("EEEE, dd MMM yyyy HH:mm:ss z"))
        assertEquals("S, 08 Sep 2018 04:08:09 UTC", dt.format("EEEEE, dd MMM yyyy HH:mm:ss z"))
        assertEquals("Sa, 08 Sep 2018 04:08:09 UTC", dt.format("EEEEEE, dd MMM yyyy HH:mm:ss z"))

        assertEquals("Sat, 8 Sep 2018 04:08:09 UTC", dt.format("EEE, d MMM yyyy HH:mm:ss z"))

        assertEquals("Sat, 08 9 2018 04:08:09 UTC", dt.format("EEE, dd M yyyy HH:mm:ss z"))
        assertEquals("Sat, 08 09 2018 04:08:09 UTC", dt.format("EEE, dd MM yyyy HH:mm:ss z"))
        assertEquals("Sat, 08 September 2018 04:08:09 UTC", dt.format("EEE, dd MMMM yyyy HH:mm:ss z"))
        assertEquals("Sat, 08 S 2018 04:08:09 UTC", dt.format("EEE, dd MMMMM yyyy HH:mm:ss z"))

        assertEquals("Sat, 08 Sep 2018 04:08:09 UTC", dt.format("EEE, dd MMM y HH:mm:ss z"))
        assertEquals("Sat, 08 Sep 18 04:08:09 UTC", dt.format("EEE, dd MMM yy HH:mm:ss z"))
        assertEquals("Sat, 08 Sep 018 04:08:09 UTC", dt.format("EEE, dd MMM yyy HH:mm:ss z"))
        assertEquals("Sat, 08 Sep 2018 04:08:09 UTC", dt.format("EEE, dd MMM yyyy HH:mm:ss z"))

        assertEquals("Sat, 08 Sep 2018 04:08:09 UTC", dt.format("EEE, dd MMM YYYY HH:mm:ss z"))

        assertEquals("Sat, 08 Sep 2018 4:08:09 UTC", dt.format("EEE, dd MMM yyyy H:mm:ss z"))

        assertEquals("Sat, 08 Sep 2018 4:08:09 am UTC", dt.format("EEE, dd MMM yyyy h:mm:ss a z"))
        assertEquals("Sat, 08 Sep 2018 04:08:09 am UTC", dt.format("EEE, dd MMM yyyy hh:mm:ss a z"))

        assertEquals("Sat, 08 Sep 2018 04:8:09 UTC", dt.format("EEE, dd MMM yyyy HH:m:ss z"))

        assertEquals("Sat, 08 Sep 2018 04:08:9 UTC", dt.format("EEE, dd MMM yyyy HH:mm:s z"))
    }

    @Test
    fun testFormattingToCustomDateTimeFormatsWithMilliseconds999() {
        val dt = DateTime(2018, 9, 8, 4, 8, 9, 999)
        assertEquals("Sat, 08 Sep 2018 04:08:9.9 UTC", dt.format("EEE, dd MMM yyyy HH:mm:s.S z"))
        assertEquals("Sat, 08 Sep 2018 04:08:9.99 UTC", dt.format("EEE, dd MMM yyyy HH:mm:s.SS z"))
        assertEquals("Sat, 08 Sep 2018 04:08:9.999 UTC", dt.format("EEE, dd MMM yyyy HH:mm:s.SSS z"))
        assertEquals("Sat, 08 Sep 2018 04:08:9.9990 UTC", dt.format("EEE, dd MMM yyyy HH:mm:s.SSSS z"))
        assertEquals("Sat, 08 Sep 2018 04:08:9.99900 UTC", dt.format("EEE, dd MMM yyyy HH:mm:s.SSSSS z"))
        assertEquals("Sat, 08 Sep 2018 04:08:9.999000 UTC", dt.format("EEE, dd MMM yyyy HH:mm:s.SSSSSS z"))
    }

    @Test
    fun testFormattingToCustomDateTimeFormatsWithMilliseconds009() {
        val dt = DateTime(2018, 9, 8, 4, 8, 9, 9)
        assertEquals("Sat, 08 Sep 2018 04:08:9.0 UTC", dt.format("EEE, dd MMM yyyy HH:mm:s.S z"))
        assertEquals("Sat, 08 Sep 2018 04:08:9.00 UTC", dt.format("EEE, dd MMM yyyy HH:mm:s.SS z"))
        assertEquals("Sat, 08 Sep 2018 04:08:9.009 UTC", dt.format("EEE, dd MMM yyyy HH:mm:s.SSS z"))
        assertEquals("Sat, 08 Sep 2018 04:08:9.0090 UTC", dt.format("EEE, dd MMM yyyy HH:mm:s.SSSS z"))
        assertEquals("Sat, 08 Sep 2018 04:08:9.00900 UTC", dt.format("EEE, dd MMM yyyy HH:mm:s.SSSSS z"))
        assertEquals("Sat, 08 Sep 2018 04:08:9.009000 UTC", dt.format("EEE, dd MMM yyyy HH:mm:s.SSSSSS z"))
    }

    @Test
    fun testParsingDateTimesInCustomStringFormats() {
        val dtmilli = 1536379689000L
        assertEquals(dtmilli, DateTime(2018, 9, 8, 4, 8, 9).unixMillisLong)
        assertEquals(
            message = "Sat, 08 Sep 2018 04:08:09 UTC",
            expected = dtmilli,
            actual = DateFormat("EEE, dd MMM yyyy HH:mm:ss z").parseLong("Sat, 08 Sep 2018 04:08:09 UTC")
        )
        assertEquals(
            message = "Saturday, 08 Sep 2018 04:08:09 UTC",
            expected = dtmilli,
            actual = DateFormat("EEEE, dd MMM yyyy HH:mm:ss z").parseLong("Saturday, 08 Sep 2018 04:08:09 UTC")
        )
        assertEquals(
            message = "S, 08 Sep 2018 04:08:09 UTC",
            expected = dtmilli,
            actual = DateFormat("EEEEE, dd MMM yyyy HH:mm:ss z").parseLong("S, 08 Sep 2018 04:08:09 UTC")
        )
        assertEquals(
            message = "Sa, 08 Sep 2018 04:08:09 UTC",
            expected = dtmilli,
            actual = DateFormat("EEEEEE, dd MMM yyyy HH:mm:ss z").parseLong("Sa, 08 Sep 2018 04:08:09 UTC")
        )

        assertEquals(
            message = "Sat, 8 Sep 2018 04:08:09 UTC",
            expected = dtmilli,
            actual = DateFormat("EEE, d MMM yyyy HH:mm:ss z").parseLong("Sat, 8 Sep 2018 04:08:09 UTC")
        )

        assertEquals(
            message = "Sat, 08 9 2018 04:08:09 UTC",
            expected = dtmilli,
            actual = DateFormat("EEE, dd M yyyy HH:mm:ss z").parseLong("Sat, 08 9 2018 04:08:09 UTC")
        )
        assertEquals(
            message = "Sat, 08 09 2018 04:08:09 UTC",
            expected = dtmilli,
            actual = DateFormat("EEE, dd MM yyyy HH:mm:ss z").parseLong("Sat, 08 09 2018 04:08:09 UTC")
        )
        assertEquals(
            message = "Sat, 08 September 2018 04:08:09 UTC",
            expected = dtmilli,
            actual = DateFormat("EEE, dd MMMM yyyy HH:mm:ss z").parseLong("Sat, 08 September 2018 04:08:09 UTC")
        )
        assertEquals(
            message = "Sat, 08 S 2018 04:08:09 UTC",
            expected = null,
            actual = DateFormat("EEE, dd MMMMM yyyy HH:mm:ss z").parseDoubleOrNull("Sat, 08 S 2018 04:08:09 UTC")
        )

        assertEquals(
            message = "Sat, 08 Sep 2018 04:08:09 UTC - y",
            expected = dtmilli,
            actual = DateFormat("EEE, dd MMM y HH:mm:ss z").parseLong("Sat, 08 Sep 2018 04:08:09 UTC")
        )
        assertEquals(
            message = "Sat, 08 Sep 18 04:08:09 UTC - yy",
            expected = null,
            actual = DateFormat("EEE, dd MMM yy HH:mm:ss z").parseDoubleOrNull("Sat, 08 Sep 18 04:08:09 UTC")
        )
        assertEquals(
            message = "Sat, 08 Sep 018 04:08:09 UTC - yyy",
            expected = dtmilli,
            actual = DateFormat("EEE, dd MMM yyy HH:mm:ss z").parseLong("Sat, 08 Sep 018 04:08:09 UTC")
        )


        assertEquals(
            message = "Sat, 08 Sep 2018 04:08:09 UTC - YYYY",
            expected = dtmilli,
            actual = DateFormat("EEE, dd MMM YYYY HH:mm:ss z").parseLong("Sat, 08 Sep 2018 04:08:09 UTC")
        )

        assertEquals(
            message = "Sat, 08 Sep 2018 4:08:09 UTC",
            expected = dtmilli,
            actual = DateFormat("EEE, dd MMM yyyy H:mm:ss z").parseLong("Sat, 08 Sep 2018 4:08:09 UTC")
        )

        assertEquals(
            message = "Sat, 08 Sep 2018 04:08:09 am UTC",
            expected = dtmilli,
            actual = DateFormat("EEE, dd MMM yyyy HH:m:ss z").parseLong("Sat, 08 Sep 2018 04:8:09 UTC")
        )

        assertEquals(
            message = "Sat, 08 Sep 2018 04:08:9 UTC",
            expected = dtmilli,
            actual = DateFormat("EEE, dd MMM yyyy HH:mm:s z").parseLong("Sat, 08 Sep 2018 04:08:9 UTC")
        )
    }

    @Test
    fun testParsingDateTimesInCustomStringFormatsWithAmPm() {
        val amDtmilli = 1536379689000L
        assertEquals(amDtmilli, DateTime(2018, 9, 8, 4, 8, 9).unixMillisLong)

        val pmDtmilli = 1536422889000L
        assertEquals(pmDtmilli, DateTime(2018, 9, 8, 16, 8, 9).unixMillisLong)

        assertEquals(
            message = "Sat, 08 Sep 2018 4:08:09 am UTC",
            expected = amDtmilli,
            actual = DateFormat("EEE, dd MMM yyyy h:mm:ss a z").parseLong("Sat, 08 Sep 2018 4:08:09 am UTC")
        )
        assertEquals(
            message = "Sat, 08 Sep 2018 04:08:09 am UTC",
            expected = amDtmilli,
            actual = DateFormat("EEE, dd MMM yyyy hh:mm:ss a z").parseLong("Sat, 08 Sep 2018 04:08:09 am UTC")
        )

        assertEquals(
            message = "Sat, 08 Sep 2018 4:08:09 pm UTC",
            expected = pmDtmilli,
            actual = DateFormat("EEE, dd MMM yyyy h:mm:ss a z").parseLong("Sat, 08 Sep 2018 4:08:09 pm UTC")
        )
        assertEquals(
            message = "Sat, 08 Sep 2018 04:08:09 pm UTC",
            expected = pmDtmilli,
            actual = DateFormat("EEE, dd MMM yyyy hh:mm:ss a z").parseLong("Sat, 08 Sep 2018 04:08:09 pm UTC")
        )
    }

    @Test
    fun testParsingDateTimesWithPmMixedWith24Hourformat() {
        val pmDtmilli = 1536422889000L
        assertEquals(pmDtmilli, DateTime(2018, 9, 8, 16, 8, 9).unixMillisLong)

        assertEquals(
            message = "Sat, 08 Sep 2018 4:08:09 pm UTC",
            expected = pmDtmilli,
            actual = DateFormat("EEE, dd MMM yyyy H:mm:ss a z").parseLong("Sat, 08 Sep 2018 16:08:09 pm UTC")
        )
        assertEquals(
            message = "Sat, 08 Sep 2018 04:08:09 pm UTC",
            expected = pmDtmilli,
            actual = DateFormat("EEE, dd MMM yyyy HH:mm:ss a z").parseLong("Sat, 08 Sep 2018 16:08:09 pm UTC")
        )
    }

    @Test
    fun testParsingDateTimesWithDeciSeconds() {
        var dtmilli = 1536379689009L
        assertEquals(dtmilli, DateTime(2018, 9, 8, 4, 8, 9, 9).unixMillisLong)
        assertEquals(
            message = "Sat, 08 Sep 2018 04:08:09.9 UTC",
            expected = dtmilli,
            actual = DateFormat("EEE, dd MMM yyyy HH:mm:ss.S z").parseLong("Sat, 08 Sep 2018 04:08:09.9 UTC")
        )
    }

    @Test
    fun testParsingDateTimesWithCentiSeconds() {
        var dtmilli = 1536379689099L
        assertEquals(dtmilli, DateTime(2018, 9, 8, 4, 8, 9, 99).unixMillisLong)
        assertEquals(
            message = "Sat, 08 Sep 2018 04:08:09.99 UTC",
            expected = dtmilli,
            actual = DateFormat("EEE, dd MMM yyyy HH:mm:ss.SS z").parseLong("Sat, 08 Sep 2018 04:08:09.99 UTC")
        )
    }

    @Test
    fun testParsingDateTimesWithMilliseconds() {
        val dtmilli = 1536379689999L
        assertEquals(dtmilli, DateTime(2018, 9, 8, 4, 8, 9, 999).unixMillisLong)
        assertEquals(
            message = "Sat, 08 Sep 2018 04:08:09.999 UTC",
            expected = dtmilli,
            actual = DateFormat("EEE, dd MMM yyyy HH:mm:ss.SSS z").parseLong("Sat, 08 Sep 2018 04:08:09.999 UTC")
        )
    }

    @Test
    fun testParsingDateTimesWithGreaterPrecisionThanMillisecond() {
        val dtmilli = 1536379689999L
        assertEquals(dtmilli, DateTime(2018, 9, 8, 4, 8, 9, 999).unixMillisLong)
        assertEquals(
            message = "Sat, 08 Sep 2018 04:08:09.9999 UTC",
            expected = dtmilli,
            actual = DateFormat("EEE, dd MMM yyyy HH:mm:ss.SSSS z").parseLong("Sat, 08 Sep 2018 04:08:09.9999 UTC")
        )
        assertEquals(
            message = "Sat, 08 Sep 2018 04:08:09.99999 UTC",
            expected = dtmilli,
            actual = DateFormat("EEE, dd MMM yyyy HH:mm:ss.SSSSS z").parseLong("Sat, 08 Sep 2018 04:08:09.99999 UTC")
        )
        assertEquals(
            message = "Sat, 08 Sep 2018 04:08:09.999999 UTC",
            expected = dtmilli,
            actual = DateFormat("EEE, dd MMM yyyy HH:mm:ss.SSSSSS z").parseLong("Sat, 08 Sep 2018 04:08:09.999999 UTC")
        )
    }


    @Test
    fun testParse() {
        assertEquals("Mon, 18 Sep 2017 04:58:45 UTC", HttpDate.format(1505710725916L))
    }

    @Test
    fun testReverseParse() {
        val STR = "Tue, 19 Sep 2017 00:58:45 UTC"
        assertEquals(STR, HttpDate.format(HttpDate.parse(STR)))
    }

    @Test
    fun testCheckedCreation() {
        assertEquals("Mon, 18 Sep 2017 23:58:45 UTC", HttpDate.format(DateTime(2017, 9, 18, 23, 58, 45)))
    }

    @Test
    fun testCreatedAdjusted() {
        assertEquals(
            "Thu, 18 Jan 2018 23:58:45 UTC",
            HttpDate.format(DateTime.createAdjusted(2017, 13, 18, 23, 58, 45))
        )
        assertEquals("Mon, 18 Sep 2017 23:58:45 UTC", HttpDate.format(DateTime.createAdjusted(2017, 9, 18, 23, 58, 45)))
        assertEquals(
            "Mon, 01 Jan 2018 00:00:01 UTC",
            HttpDate.format(DateTime.createAdjusted(2017, 12, 31, 23, 59, 61))
        )
        assertEquals(
            "Thu, 21 Mar 2024 19:32:20 UTC",
            HttpDate.format(DateTime.createAdjusted(2017, 12, 31, 23, 59, 200_000_000))
        )
    }

    @Test
    fun testCreatedClamped() {
        assertEquals("Mon, 18 Sep 2017 23:58:45 UTC", HttpDate.format(DateTime.createClamped(2017, 9, 18, 23, 58, 45)))
        assertEquals("Mon, 18 Dec 2017 23:58:45 UTC", HttpDate.format(DateTime.createClamped(2017, 13, 18, 23, 58, 45)))
    }

    @Test
    fun testSpecial() {
        assertEquals("Mon, 01 Jan 0001 00:00:00 UTC", HttpDate.format(DateTime.createClamped(1, 1, 1, 0, 0, 0, 0)))
    }

    @Test
    fun testBaseAdjust() {
        val date = DateTime(Year(2018), Month.November, 4, 5, 54, 30)

        assertEquals("Sun, 04 Nov 2018 05:54:30 GMT+0100", date.toOffsetUnadjusted((+60).minutes).toString())
        assertEquals("Sun, 04 Nov 2018 06:54:30 GMT+0100", date.toOffset((+60).minutes).toString())
    }
}

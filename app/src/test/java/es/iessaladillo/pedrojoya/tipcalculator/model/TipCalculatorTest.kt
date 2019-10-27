package es.iessaladillo.pedrojoya.tipcalculator.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class TipCalculatorTest {

    @DisplayName("Should throw IllegalArgumentException when bill is negative")
    @Test
    fun `should throw IllegalArgumentException when bill is negative`() {
        assertThrows(IllegalArgumentException::class.java) { tipCalculator(-1f, 10f, 1) }
    }

    @DisplayName("Should throw IllegalArgumentException when percentage is negative")
    @Test
    fun `should throw IllegalArgumentException when percentage is negative`() {
        assertThrows(IllegalArgumentException::class.java) { tipCalculator(10f, -1f, 1) }
    }

    @DisplayName("Should throw IllegalArgumentException when diners is not positive")
    @ParameterizedTest
    @ValueSource(ints = [-1, 0])
    fun `should throw IllegalArgumentException when diners is not positive`(diners: Int) {
        assertThrows(IllegalArgumentException::class.java) { tipCalculator(1f, 1f, diners) }
    }

    @DisplayName("Should calculate total properly")
    @Test
    fun `should calculate total properly`() {
        val tipCalculator = tipCalculator(100f, 20f, 1)
        assertEquals(120f, tipCalculator.calculateTotal())
    }

    @DisplayName("Should calculate total properly with bill 0")
    @Test
    fun `should calculate total properly with bill 0`() {
        val tipCalculator = tipCalculator(0f, 0f, 1)
        assertEquals(0f, tipCalculator.calculateTotal())
    }

    @DisplayName("Should calculate total properly with percentage 0")
    @Test
    fun `should calculate total properly with percentage 0`() {
        val tipCalculator = tipCalculator(100f, 0f, 1)
        assertEquals(100f, tipCalculator.calculateTotal())
    }

    @DisplayName("Should calculate perDiner properly")
    @Test
    fun `should calculate perDiner properly`() {
        val tipCalculator = tipCalculator(100f, 20f, 1)
        assertEquals(120f, tipCalculator.calculatePerDiner())
    }

    @DisplayName("Should calculate perDinerRounded properly")
    @Test
    fun `should calculate perDinerRounded properly`() {
        val tipCalculator = tipCalculator(100f, 20f, 1)
        assertEquals(120f, tipCalculator.calculatePerDinerRounded())
    }

    @DisplayName("Should calculate perDinerRounded properly when perDinerRounded has 00 as cents")
    @Test
    fun `should calculate perDinerRounded properly when perDinerRounded has 00 as cents`() {
        val tipCalculator = tipCalculator(100f, 20f, 1)
        val perDiner = tipCalculator.calculatePerDiner()
        assertEquals(120.00f, perDiner)
    }

}
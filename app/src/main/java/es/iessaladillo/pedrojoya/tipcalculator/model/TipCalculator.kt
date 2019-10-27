package es.iessaladillo.pedrojoya.tipcalculator.model

class tipCalculator(var bill: Float, var porcentage: Float, var dinners: Int) {

    init {
        checkProperty()
    }

    private fun checkProperty() {
        require(bill >= 0) { "The bill can`t is negative." }
        require(porcentage >= 0) { "The porcentage can`t is negative." }
        require(dinners > 0) { "The dinners can`t is negative or zero" }
    }


    fun calculateTip() = bill / 100 * porcentage

    fun calculateTotal() = calculateTip() + bill

    fun calculatePerDiner() = calculateTotal() / dinners

    fun calculatePerDinerRounded() = Math.ceil(calculatePerDiner().toDouble()).toFloat()

}
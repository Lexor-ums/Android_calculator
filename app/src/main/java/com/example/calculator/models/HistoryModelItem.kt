package com.example.calculator.models

import java.math.BigDecimal

data class HistoryModelItem(var leftVal : BigDecimal,
                        var rightVal : BigDecimal,
                        var operation: String,
                        var result  :BigDecimal)
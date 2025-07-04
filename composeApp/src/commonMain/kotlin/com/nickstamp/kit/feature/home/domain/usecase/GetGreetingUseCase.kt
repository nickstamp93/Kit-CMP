package com.nickstamp.kit.feature.home.domain.usecase

import com.nickstamp.kit.Greeting

class GetGreetingUseCase {
    suspend fun invoke(): String {
        return Greeting().greet()
    }
}
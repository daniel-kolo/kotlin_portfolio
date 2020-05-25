package com.kotlin_portfolio.repo

import com.kotlin_portfolio.domain.Portfolio
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PortfolioRepository : JpaRepository<Portfolio, Long> {
}
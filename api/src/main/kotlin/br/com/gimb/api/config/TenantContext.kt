package br.com.gimb.api.config

class TenantContext {

    companion object {
        private val currentTenant: ThreadLocal<Any> = ThreadLocal()

        fun setCurrentTenant(tenant: Any) {
            currentTenant.set(tenant)
        }

        fun getCurrentTenant() : Any? {
            return currentTenant.get()
        }
    }

}
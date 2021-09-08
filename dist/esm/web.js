import { WebPlugin } from '@capacitor/core';
export class ProximityWeb extends WebPlugin {
    async getProximity() {
        throw this.unimplemented('Not implemented on web.');
    }
    async getStatus() {
        throw this.unimplemented('Not implemented on web.');
    }
    async start() {
        throw this.unimplemented('Not implemented on web.');
    }
    async stop() {
        throw this.unimplemented('Not implemented on web.');
    }
}
//# sourceMappingURL=web.js.map
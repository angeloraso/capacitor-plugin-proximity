var capacitorProximity = (function (exports, core) {
    'use strict';

    const Proximity = core.registerPlugin('Proximity', {
        web: () => Promise.resolve().then(function () { return web; }).then(m => new m.ProximityWeb()),
    });

    class ProximityWeb extends core.WebPlugin {
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

    var web = /*#__PURE__*/Object.freeze({
        __proto__: null,
        ProximityWeb: ProximityWeb
    });

    exports.Proximity = Proximity;

    Object.defineProperty(exports, '__esModule', { value: true });

    return exports;

}({}, capacitorExports));
//# sourceMappingURL=plugin.js.map

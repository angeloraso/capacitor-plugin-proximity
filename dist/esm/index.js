import { registerPlugin } from '@capacitor/core';
const Proximity = registerPlugin('Proximity', {
    web: () => import('./web').then(m => new m.ProximityWeb()),
});
export * from './definitions';
export { Proximity };
//# sourceMappingURL=index.js.map
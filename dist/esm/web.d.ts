import { WebPlugin } from '@capacitor/core';
import type { ProximityPlugin, PROXIMITY, STATUS } from './definitions';
export declare class ProximityWeb extends WebPlugin implements ProximityPlugin {
    getProximity(): Promise<{
        proximity: PROXIMITY;
    }>;
    getStatus(): Promise<{
        status: STATUS;
    }>;
    start(): Promise<void>;
    stop(): Promise<void>;
}

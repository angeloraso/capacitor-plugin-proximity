import { WebPlugin } from '@capacitor/core';

import type { ProximityPlugin, PROXIMITY, STATUS } from './definitions';

export class ProximityWeb extends WebPlugin implements ProximityPlugin {
  async getProximity(): Promise<{proximity: PROXIMITY}>{
    throw this.unimplemented('Not implemented on web.');
  }
  async getStatus(): Promise<{status: STATUS}>{
    throw this.unimplemented('Not implemented on web.');
  }
  async start(): Promise<void>{
    throw this.unimplemented('Not implemented on web.');
  }
  async stop(): Promise<void>{
    throw this.unimplemented('Not implemented on web.');
  }
}

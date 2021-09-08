export type PROXIMITY = 'NEAR' | 'FAR';
export type STATUS = 'STOPPED' | 'STARTING' | 'RUNNING' | 'ERROR_FAILED_TO_START';
export interface ProximityPlugin {
  getProximity(): Promise<{proximity: PROXIMITY}>;
  getStatus(): Promise<{status: STATUS}>;
  start(): Promise<void>;
  stop(): Promise<void>;
}

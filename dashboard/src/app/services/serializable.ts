/**
 * Makes a class serializable
 */
export class Serializable {
    constructor(json?: any) {
        if (json) {
            Object.assign(this, json);
        }
    }
}
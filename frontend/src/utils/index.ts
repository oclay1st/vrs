
export function required(rule: any, value: any) {
    return new Promise((resolve: Function, reject: Function) => {
        if (!value) {
            reject('This field is required');
        } else {
            resolve();
        }
    })
}

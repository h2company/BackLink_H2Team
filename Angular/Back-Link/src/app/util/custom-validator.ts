import { AbstractControl, ValidatorFn } from '@angular/forms';

export const requiredValidator = (): ValidatorFn => {
    return (control: AbstractControl): {[key: string]: string } =>{
        return control.value && control.value.length > 0 ? null : { "message" : 'Trường dữ liệu bắt buộc'}
    }
}

export const emailValidator = (): ValidatorFn => {
    return (control: AbstractControl): {[key: string]: string } =>{
        const result = /^[a-z][a-z0-9_\.]{5,32}@[a-z0-9]{2,}(\.[a-z0-9]{2,4}){1,2}$/i.test(control.value);
        return result ? null : { "message" : 'Email không hợp lệ'}
    }
}

export const phoneValidator = (): ValidatorFn => {
    return (control: AbstractControl): {[key: string]: string } =>{
        const result = /^0(\d{9})$/i.test(control.value);
        return result ? null : { "message" : 'Số điện thoại không hợp lệ'}
    }
}
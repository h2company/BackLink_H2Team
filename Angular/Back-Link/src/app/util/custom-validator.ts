import { AbstractControl, ValidatorFn } from '@angular/forms';

export const requiredValidator = (): ValidatorFn => {
    return (control: AbstractControl): {[key: string]: string } =>{
        return control.value && control.value.length > 0 ? null : { "message" : 'Trường dữ liệu bắt buộc'}
    }
}

export const emailValidator = (): ValidatorFn => {
    return (control: AbstractControl): {[key: string]: string } =>{
        const result = /^[a-z][a-z0-9_\.]{5,32}@[a-z0-9]{2,}(\.[a-z0-9]{2,4}){1,2}$/i.test(control.value);
        return result ? null : { "message" : 'Email không hợp lệ. VD: example@gmail.com'}
    }
}

export const phoneValidator = (): ValidatorFn => {
    return (control: AbstractControl): {[key: string]: string } =>{
        const result = /^0(\d{9})$/i.test(control.value);
        return result ? null : { "message" : 'Số điện thoại không hợp lệ. VD: 0986015392'}
    }
}

export const lengthValidator = (min: number, max: number): ValidatorFn => {
    return (control: AbstractControl): { [key: string]: string } | null => {
        if ((control.value.length < min || control.value.length > max)) {
            return { "message" : 'Giá trị không hợp lệ (Độ dài tối thiểu '+min+', tối đa '+max+')'};
        }
        return null;
    };
}

export const minmaxValidator = (min: number, max: number): ValidatorFn => {
    return (control: AbstractControl): { [key: string]: string } | null => {
        if (!isNaN(control.value) && (control.value < min || control.value > max)) {
            return { "message" : 'Giá trị không hợp lệ (tối thiểu '+min+', tối đa '+max+')'};
        }
        return null;
    };
}
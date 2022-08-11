import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'orderBy'
})
export class OrderByPipe implements PipeTransform {

  transform(value: string[] | number[],
            expression,
            reverse?: boolean,
            isCaseInsensitive: boolean = false,
  ): any {
    if (Array.isArray(value)) {
      return this.sortArray(
        value.slice(),
        expression,
        reverse,
        isCaseInsensitive
      );
    }

    throw new Error("orderBy supports arrays only.")
  }

  private sortArray(
    value: any[],
    expression?: any,
    reverse?: boolean,
    isCaseInsensitive?: boolean,
  ): any[] {

    const isDeepLink = expression && expression.indexOf(".") !== -1;

    if (isDeepLink) {
      expression = OrderByPipe.parseExpression(expression);
    }
    let compareFn: Function;

    compareFn = isCaseInsensitive
      ? OrderByPipe.caseInsensitiveSort
      : OrderByPipe.defaultCompare;

    const array: any[] = value.sort((a: any, b: any): number => {
      if (!expression) {
        return compareFn(a, b);
      }

      if (!isDeepLink) {
        if (a && b) {
          return compareFn(a[expression], b[expression]);
        }
        return compareFn(a, b);
      }

      return compareFn(
        OrderByPipe.getValue(a, expression),
        OrderByPipe.getValue(b, expression)
      );
    });

    if (reverse) {
      return array.reverse();
    }

    return array;
  }

  static getValue(object: any, expression: string[]) {
    for (let i = 0, n = expression.length; i < n; ++i) {
      if (!object) {
        return;
      }
      const k = expression[i];
      if (!(k in object)) {
        return;
      }
      if (typeof object[k] === "function") {
        object = object[k]();
      } else {
        object = object[k];
      }
    }

    return object;
  }

  static caseInsensitiveSort(a: any, b: any) {
    if (OrderByPipe.isString(a) && OrderByPipe.isString(b)) {
      return a.localeCompare(b);
    }
    return OrderByPipe.defaultCompare(a, b);
  }

  static isString(value: any) {
    return typeof value === "string" || value instanceof String;
  }
  static defaultCompare(a: any, b: any) {
    if (a === b) {
      return 0;
    }
    if (a == null) {
      return 1;
    }
    if (b == null) {
      return -1;
    }
    return a > b ? 1 : -1;
  }

  static parseExpression(expression: string): string[] {
    expression = expression.replace(/\[(\w+)\]/g, ".$1");
    expression = expression.replace(/^\./, "");
    return expression.split(".");
  }

}

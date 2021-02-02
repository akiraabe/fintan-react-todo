/* tslint:disable */
/* eslint-disable */
/**
 * ToDo REST API
 * サービス開発ハンズオンで作成するToDoアプリのREST API。
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


import * as runtime from '../runtime';
import {
    InlineObject,
    InlineObjectFromJSON,
    InlineObjectToJSON,
    InlineObject1,
    InlineObject1FromJSON,
    InlineObject1ToJSON,
    Todo,
    TodoFromJSON,
    TodoToJSON,
} from '../models';

export interface DeleteTodoRequest {
    todoId: number;
}

export interface PostTodoRequest {
    inlineObject: InlineObject;
}

export interface PutTodoRequest {
    todoId: number;
    inlineObject1: InlineObject1;
}

/**
 * 
 */
export class TodosApi extends runtime.BaseAPI {

    /**
     * 登録しているToDoを削除する。 
     * ToDoの削除
     */
    async deleteTodoRaw(requestParameters: DeleteTodoRequest): Promise<runtime.ApiResponse<void>> {
        if (requestParameters.todoId === null || requestParameters.todoId === undefined) {
            throw new runtime.RequiredError('todoId','Required parameter requestParameters.todoId was null or undefined when calling deleteTodo.');
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/api/todos/{todoId}`.replace(`{${"todoId"}}`, encodeURIComponent(String(requestParameters.todoId))),
            method: 'DELETE',
            headers: headerParameters,
            query: queryParameters,
        });

        return new runtime.VoidApiResponse(response);
    }

    /**
     * 登録しているToDoを削除する。 
     * ToDoの削除
     */
    async deleteTodo(requestParameters: DeleteTodoRequest): Promise<void> {
        await this.deleteTodoRaw(requestParameters);
    }

    /**
     * 登録しているToDoを全て取得する。 
     * ToDo一覧の取得
     */
    async getTodosRaw(): Promise<runtime.ApiResponse<Array<Todo>>> {
        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/api/todos`,
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        });

        return new runtime.JSONApiResponse(response, (jsonValue) => jsonValue.map(TodoFromJSON));
    }

    /**
     * 登録しているToDoを全て取得する。 
     * ToDo一覧の取得
     */
    async getTodos(): Promise<Array<Todo>> {
        const response = await this.getTodosRaw();
        return await response.value();
    }

    /**
     * ToDoを登録する。 
     * ToDoの登録
     */
    async postTodoRaw(requestParameters: PostTodoRequest): Promise<runtime.ApiResponse<Todo>> {
        if (requestParameters.inlineObject === null || requestParameters.inlineObject === undefined) {
            throw new runtime.RequiredError('inlineObject','Required parameter requestParameters.inlineObject was null or undefined when calling postTodo.');
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        headerParameters['Content-Type'] = 'application/json';

        const response = await this.request({
            path: `/api/todos`,
            method: 'POST',
            headers: headerParameters,
            query: queryParameters,
            body: InlineObjectToJSON(requestParameters.inlineObject),
        });

        return new runtime.JSONApiResponse(response, (jsonValue) => TodoFromJSON(jsonValue));
    }

    /**
     * ToDoを登録する。 
     * ToDoの登録
     */
    async postTodo(requestParameters: PostTodoRequest): Promise<Todo> {
        const response = await this.postTodoRaw(requestParameters);
        return await response.value();
    }

    /**
     * ToDoのステータスを更新する。 
     * ToDoステータスの更新
     */
    async putTodoRaw(requestParameters: PutTodoRequest): Promise<runtime.ApiResponse<Todo>> {
        if (requestParameters.todoId === null || requestParameters.todoId === undefined) {
            throw new runtime.RequiredError('todoId','Required parameter requestParameters.todoId was null or undefined when calling putTodo.');
        }

        if (requestParameters.inlineObject1 === null || requestParameters.inlineObject1 === undefined) {
            throw new runtime.RequiredError('inlineObject1','Required parameter requestParameters.inlineObject1 was null or undefined when calling putTodo.');
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        headerParameters['Content-Type'] = 'application/json';

        const response = await this.request({
            path: `/api/todos/{todoId}`.replace(`{${"todoId"}}`, encodeURIComponent(String(requestParameters.todoId))),
            method: 'PUT',
            headers: headerParameters,
            query: queryParameters,
            body: InlineObject1ToJSON(requestParameters.inlineObject1),
        });

        return new runtime.JSONApiResponse(response, (jsonValue) => TodoFromJSON(jsonValue));
    }

    /**
     * ToDoのステータスを更新する。 
     * ToDoステータスの更新
     */
    async putTodo(requestParameters: PutTodoRequest): Promise<Todo> {
        const response = await this.putTodoRaw(requestParameters);
        return await response.value();
    }

}

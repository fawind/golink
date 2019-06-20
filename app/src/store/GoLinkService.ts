export class GoLink {
  constructor(
      private readonly _alias: string,
      private readonly _url: string,
      private readonly _created: Date) {
  }

  get alias(): string {
    return this._alias;
  }

  get url(): string {
    return this._url;
  }

  get created(): Date {
    return this._created;
  }

  get createdString(): string {
    return this._created.toISOString().substring(0, 10);
  }

  static fromJSON(json: any): GoLink {
    if (!json.alias || !json.url || !json.created) {
      throw new Error(`Cannot parse json to GoLink: ${json}`);
    }
    return new GoLink(json.alias, json.url, new Date(json.created));
  }
}

export class GoLinkService {
  private static readonly API_ENDPOINT = '/api/v1/golink';
  private static readonly HEADERS = new Headers({'Content-Type': 'Application/json'});

  static async getGoLinks(): Promise<GoLink[]> {
    const response = await this.sendRequest(this.API_ENDPOINT, {});
    const data = await response.json();
    return data.map((elem: any) => GoLink.fromJSON(elem));
  }

  static async addGoLink(alias: string, url: string) {
    await this.sendRequest(this.API_ENDPOINT, {
      method: 'POST',
      headers: this.HEADERS,
      body: JSON.stringify({alias, url}),
    });
  }

  static async deleteGoLink(goLink: GoLink) {
    await this.sendRequest(`${this.API_ENDPOINT}/${goLink.alias}`, {method: 'DELETE'});
  }

  private static async sendRequest(url: string, options: any): Promise<Response> {
    const response = await fetch(url, options);
    if (!response.ok) {
      throw new Error(response.statusText);
    }
    return response;
  }
}

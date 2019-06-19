export class GoLink {
  constructor(
      private readonly _alias: string,
      private readonly _url: URL,
      private readonly _created: Date) {
  }

  get alias(): string {
    return this._alias;
  }

  get url(): URL {
    return this._url;
  }

  get created(): Date {
    return this._created;
  }

  static fromJSON(json: any): GoLink {
    if (!json.alias || !json.url || !json.created) {
      throw new Error(`Cannot parse json to GoLink: ${json}`);
    }
    return new GoLink(json.alias, json.url, json.created);
  }
}

export class GoLinkService {
  static readonly API_ENDPOINT = '/go';

  static async getGoLinks(): Promise<GoLink[]> {
    const response = await fetch(this.API_ENDPOINT);
    const data = await response.json();
    return data.map((elem: any) => GoLink.fromJSON(elem));
  }

  static async addGoLink(alias: string, url: string) {
    await fetch(this.API_ENDPOINT, {
      method: 'POST',
      body: JSON.stringify({alias, url}),
    });
  }

  static async deleteGoLink(goLink: GoLink) {
    await fetch(`${this.API_ENDPOINT}/${goLink.alias}`, {
      method: 'DELETE',
    });
  }
}

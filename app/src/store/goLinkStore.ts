import {action, computed, observable, runInAction} from 'mobx';
import {GoLink, GoLinkService} from '@src/store/GoLinkService';

export enum MessageType {
  HIDDEN = 0,
  ERROR = 1,
}

export class Message {
  constructor(readonly message: string, readonly type: MessageType) {
  }
}

const HIDDEN_MESSAGE = new Message('', MessageType.HIDDEN);

export class GoLinkStore {
  @observable private _goLinks: GoLink[] = [];
  @observable private _loading = false;
  @observable private _message: Message = HIDDEN_MESSAGE;

  @computed
  get goLinks(): GoLink[] {
    return this._goLinks;
  }

  @computed
  get isLoading(): boolean {
    return this._loading;
  }

  @computed
  get hasMessage(): boolean {
    return this._message.type !== MessageType.HIDDEN;
  }

  @computed
  get message(): Message {
    return this._message;
  }

  @action
  clearMessage() {
    this._message = HIDDEN_MESSAGE;
  }

  @action.bound
  async fetchGoLinks() {
    await this.sendRequest(
        () => GoLinkService.getGoLinks(),
        (goLinks: GoLink[]) => this._goLinks = goLinks,
        'Error fetching GoLinks');
  }

  @action.bound
  async deleteGoLink(goLink: GoLink) {
    await this.sendRequest(
        () => GoLinkService.deleteGoLink(goLink),
        () => this._goLinks = this._goLinks.filter(g => g.alias !== goLink.alias),
        'Error deleting GoLink');
  }

  @action.bound
  async addGoLink(alias: string, url: string) {
    await this.sendRequest(
        () => GoLinkService.addGoLink(alias, url),
        () => this._goLinks.unshift(new GoLink(alias, url, new Date())),
        'Error adding GoLink');
  }

  @action.bound
  async sendRequest(
      serviceAction: () => Promise<any>,
      onActionSuccess: (result: any | undefined) => void,
      errorMsg: string): Promise<void> {
    runInAction(() => this._loading = true);
    try {
      const result = await serviceAction();
      runInAction(() => {
        this._loading = false;
        onActionSuccess(result);
      });
    } catch (error) {
      console.error(errorMsg, error);
      runInAction(() => {
        this._loading = false;
        this._message = new Message(errorMsg, MessageType.ERROR);
      });
    }
  }
}

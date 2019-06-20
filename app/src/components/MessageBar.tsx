import * as React from 'react';
import {observer} from 'mobx-react';
import {GoLinkStore, MessageType} from '@src/store/goLinkStore';

interface Props {
  store: GoLinkStore
}

export const MessageBar: React.FunctionComponent<Props> = observer((props: Props) => {
  const clearMessage = props.store.clearMessage.bind(props.store);
  if (!props.store.hasMessage) {
    return <div/>;
  }
  const typeClass = props.store.message.type === MessageType.ERROR ? 'error' : '';
  return (
      <div className={`message-bar ${typeClass}`}>
        <span>{props.store.message.message} </span>
        [<a className="clickable" onClick={clearMessage}>close</a>]
      </div>
  );
});

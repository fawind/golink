import * as React from 'react';
import {ReactElement} from 'react';
import {observer} from 'mobx-react';
import {GoLinkItem} from '@src/components/GoLinkItem';
import {GoLinkStore} from '@src/store/goLinkStore';

interface Props {
  store: GoLinkStore,
}

export const GoLinkList: React.FunctionComponent<Props> = observer((props: Props): ReactElement => {
  const goLinkElems = props.store.goLinks.map(goLink => {
    const onDelete = props.store.deleteGoLink.bind(props.store, goLink);
    return <GoLinkItem key={goLink.alias} goLink={goLink} onDelete={onDelete}/>;
  });
  return <ul>{goLinkElems}</ul>;
});

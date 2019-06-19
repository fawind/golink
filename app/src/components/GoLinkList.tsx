import * as React from 'react';
import {ReactElement} from 'react';
import {GoLinkItem} from '@src/components/GoLinkItem';
import {GoLink} from '@src/GoLinkService';

interface Props {
  goLinks: GoLink[],
}

export const GoLinkList: React.FunctionComponent<Props> = (props: Props): ReactElement => {
  const goLinkElems = props.goLinks.map(goLink =>
      <GoLinkItem key={goLink.alias} goLink={goLink}/>);
  return (
      <ul>{goLinkElems}</ul>
  );
};
